package com.example.carrentalapplication.controller.car;

import com.example.carrentalapplication.Validation.CarDetailsValidation;
import com.example.carrentalapplication.dao.CarDAO;
import com.example.carrentalapplication.dto.CarDetailsDTO;
import com.example.carrentalapplication.exception.DAOException;
import com.example.carrentalapplication.jpamodel.CarDetailsEntity;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CarDAO carDAO = new CarDAO();

        String carId = req.getParameter("carId");
        try {
            List<CarDetailsEntity> carDetails = carDAO.viewAllCarByCarId(carId);
            req.setAttribute("carDetails", carDetails);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("UpdateCar.jsp");
            requestDispatcher.forward(req, resp);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDetailsDTO carDetailsDTO = new CarDetailsDTO();


        ServletContext servletContext = getServletContext();
        Part imagePart = req.getPart("image");
        String imageFileName = getFileName(imagePart);
        String uploadDirectory = uploadFileAndGetImagePath(imagePart, imageFileName, servletContext);


        String carId = req.getParameter("carId");

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
        List<Error> errorList = CarDetailsValidation.validateCarDetails(carDetailsDTO);
        if (!errorList.isEmpty()) {
            req.setAttribute("errorList", errorList);
            req.setAttribute("carId", carId);
            req.setAttribute("carDetails", carDetailsDTO);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("UpdateCar.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            try {
                UpdateCar(carId, carDetailsDTO);
                resp.sendRedirect("view-car?carId:" + carId);
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

    public void UpdateCar(String carId, CarDetailsDTO carDetailsDTO) throws DAOException {
        CarDAO carDAO = new CarDAO();
//        CarDetailsEntity carDetailsEntity=new CarDetailsEntity();
//        carDetailsEntity.setName(carDetailsDTO.getName());
//        carDetailsEntity.setRegistrationNumber(Integer.valueOf(carDetailsDTO.getRegistrationNumber()));
//        carDetailsEntity.setChargePerDay(Integer.valueOf(carDetailsDTO.getChargePerDay()));
//        carDetailsEntity.setFuelType(carDetailsDTO.getFuelType());
//        carDetailsEntity.setInsurancePolicyNumber(Integer.valueOf(carDetailsDTO.getInsurancePolicyNumber()));
//        carDetailsEntity.setKmTravelled(Integer.valueOf(carDetailsDTO.getKmTravelled()));
//        carDetailsEntity.setNoOfSeats(Integer.valueOf(carDetailsDTO.getNoOfSeats()));
//        carDetailsEntity.setModel(Integer.valueOf(carDetailsDTO.getModel()));
//        carDetailsEntity.setTransmissionType(carDetailsDTO.getTransmissionType());
//        carDetailsEntity.setColor(carDetailsDTO.getColor());
//                carDetailsEntity.setImage(carDetailsDTO.getImage());
        carDAO.carUpdate(carDetailsDTO, carId);
    }
}

