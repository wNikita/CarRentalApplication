package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.dao.BookDao;
import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.dto.BookDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.CarDetails;
import com.example.carrentalapplication.model.User;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@MultipartConfig
public class CarDetailsServlet extends HttpServlet {
    CarDAO carDAO = new CarDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        String carID = req.getParameter("carId");
        String PickupDate=req.getParameter("pickUpDate");
        String ReturnDate=req.getParameter("returnDate");
        CarDetails carDetails = null;
        try {
            carDetails = carDAO.getAllCarDetails(carID);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            req.setAttribute("carDetails",carDetails);
            req.setAttribute("PickupDate",PickupDate);
            req.setAttribute("ReturnDate",ReturnDate);
            Date startDate =sdf.parse(PickupDate);
            Date endDate = sdf.parse(ReturnDate);

            long differenceInMillis = endDate.getTime() - startDate.getTime();
            long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis);
            req.setAttribute("TotalNumberOfDays",differenceInDays);
            double totalCharge = differenceInDays * carDetails.getChargePerDay();
            req.setAttribute("totalCharge",totalCharge);
        } catch (DAOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("CarDetails.jsp").forward(req, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");
        ServletContext servletContext = getServletContext();
        Part imagePart = req.getPart("licenseImage");


            String imageFileName = getFileName(imagePart);
            String uploadDirectory = uploadFileAndGetImagePath(imagePart, imageFileName, servletContext);

            BookDTO bookDTO = new BookDTO();

            bookDTO.setPickupDateDTO(req.getParameter("pickUpDate"));
            bookDTO.setReturnDateDTO(req.getParameter("returnDate"));
            bookDTO.setLicenseDTO(uploadDirectory);
            bookDTO.setRentalDaysDTO(req.getParameter("totalNumberOfDays"));
            bookDTO.setTotalCostDTO(req.getParameter("totalCharge"));
            bookDTO.setCarIdDTO(req.getParameter("carId"));
            bookDTO.setUserIdDTO(user.getUserId());
            BookDao bookDao = new BookDao();
            try {
                RazorpayClient    razorpay = new RazorpayClient("rzp_test_QEtxzC00WQzK7n", "w3Qf6dDLepikuqqrAks4d4LD");

                JSONObject orderRequest = new JSONObject();
                double totalCost = Double.parseDouble(bookDTO.getTotalCostDTO()); // Assuming this returns the amount in rupees

                int amountInPaise = (int) (totalCost * 100); // Convert rupees to paise

                orderRequest.put("amount", amountInPaise); // amount in the smallest currency unit
                orderRequest.put("currency", "INR");

                Order order = razorpay.Orders.create(orderRequest);
                String orderId = order.get("id");
                req.setAttribute("razorpay_order_id",orderId);
                req.setAttribute("TotalAmount",bookDTO.getTotalCostDTO());
                bookDTO.setRazorpayOrderId(orderId);
                bookDao.BookCar(bookDTO);

                RequestDispatcher requestDispatcher= req.getRequestDispatcher("Payment.jsp");
                requestDispatcher.forward(req,resp);
            } catch (DAOException e) {
                e.printStackTrace();
            } catch (RazorpayException e) {
                e.printStackTrace();
            }

    }



    public static String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }


    public static String uploadFileAndGetImagePath(Part part, String fileName, ServletContext servletContext) throws IOException {
        String basePath = servletContext.getRealPath("/");
        String savePath = basePath + "car_uploads" + File.separator + fileName;
        File outputFile = new File(savePath);

        InputStream inputStream = part.getInputStream();
        OutputStream outputStream = new FileOutputStream(outputFile);
        int read;
        byte[] buffer = new byte[4096];
        while ((read = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, read);
        }
        inputStream.close();
        outputStream.close();

        String imagePath = "car_uploads" + File.separator + fileName;
        return imagePath;
    }

}





