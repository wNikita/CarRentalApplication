package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.Validation.CarDetailsValidation;
import com.example.carrentalapplication.dao.AgencyDAO;
import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.dto.CarDetailsDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.AgencyDetails;
import com.example.carrentalapplication.model.CarDetails;
import com.example.carrentalapplication.model.User;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;

import java.util.List;

@MultipartConfig
public class AddCarServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgencyDAO agencyDAO = new AgencyDAO();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        AgencyDetails agencyDetails1 = agencyDAO.viewAgencyDetails(user.getUserId());
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("AgencyUser", agencyDetails1);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("AddCar.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDetailsDTO carDetailsDTO = new CarDetailsDTO();

        ServletContext servletContext = getServletContext();
        Part imagePart = req.getPart("image");

        if (imagePart.getSize() == 0) {
            // Display an error message
            carDetailsDTO.setImage(String.valueOf(imagePart));
            validationCar(req, resp);
        } else {
            String imageFileName = getFileName(imagePart);
            String uploadDirectory = uploadFileAndGetImagePath(imagePart, imageFileName, servletContext);
//        Part filePart = req.getPart("image");  // "image" should match the name attribute of the file input field
//
//        // Extract the filename and content type
//        String fileName = filePart.get//getSubmittedFileName();
//        String contentType = filePart.getContentType();
//
//        // Get the input stream of the uploaded file
//        InputStream imageInputStream = filePart.getInputStream();


            HttpSession session = req.getSession();
            AgencyDetails agencyDetails = (AgencyDetails) session.getAttribute("AgencyUser");
            carDetailsDTO.setName(req.getParameter("name"));
            carDetailsDTO.setRegistrationNumber(req.getParameter("registrationNumber"));
            carDetailsDTO.setChargePerDay(req.getParameter("ratePerDay"));
            carDetailsDTO.setFuelType(req.getParameter("fuelType"));
            carDetailsDTO.setInsurancePolicyNumber(req.getParameter("policyNumber"));
            carDetailsDTO.setKmTravelled(req.getParameter("kmTravelled"));
            carDetailsDTO.setNoOfSeats(req.getParameter("noOfSeats"));
            carDetailsDTO.setModel(req.getParameter("modelNumber"));
            carDetailsDTO.setTransmissionType(req.getParameter("transmissionType"));
            carDetailsDTO.setColor(req.getParameter("carColor"));
            carDetailsDTO.setAgencyId(agencyDetails.getAgencyDetailsId());
            carDetailsDTO.setImage(uploadDirectory);
            validationCar(req, resp);

        }
    }

    public void validationCar(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        CarDetailsDTO carDetailsDTO = new CarDetailsDTO();

        List<Error> errorList = CarDetailsValidation.validateCarDetails(carDetailsDTO);
        if (!errorList.isEmpty()) {
            req.setAttribute("errorList", errorList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("AddCar.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            CarDAO carDAO = new CarDAO();
            try {
                carDAO.add(carDetailsDTO);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("Admin.jsp");
                requestDispatcher.forward(req, resp);
            } catch (DAOException e) {
                e.printStackTrace();
            }
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

