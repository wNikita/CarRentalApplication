package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.Validation.CarDetailsValidation;
import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.dto.CarDetailsDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.model.CarDetails;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;

@MultipartConfig
public class UpdateCarServlet extends HttpServlet {
    private CarDAO carDAO = new CarDAO();
    private CarDetailsDTO carDetailsDTO=new CarDetailsDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String carId = req.getParameter("carId");
        try {
            CarDetails carDetails = carDAO.getAllCarDetails(carId);
            req.setAttribute("carDetails",carDetails);
        }catch (DAOException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("UpdateCar.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Part imagePart = req.getPart("image");
        String imageFileName = getFileName(imagePart);
        String uploadDirectory = uploadFileAndGetImagePath(imagePart, imageFileName, servletContext);


        int carId = Integer.parseInt(req.getParameter("carId"));

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
        carDetailsDTO.setImage(uploadDirectory);
        CarDAO carDAO = new CarDAO();
        List<Error> errorList = CarDetailsValidation.validateCarDetails(carDetailsDTO);
        if (!errorList.isEmpty()) {
            req.setAttribute("errorList", errorList);
            req.setAttribute("carId",carId);
            req.setAttribute("carDetails",carDetailsDTO);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("UpdateCar.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            try {
                carDAO.UpdateCar(carId, carDetailsDTO);
                resp.sendRedirect("view-car");
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

