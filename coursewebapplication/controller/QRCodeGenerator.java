package org.course.coursewebapplication.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRCodeGenerator {
    public static String generateQRCode(String merchantNumber, String amount, String reference) {
        String qrData = "bKash Merchant: " + merchantNumber + "\nAmount: " + amount + " BDT\nReference: " + reference;
        int width = 250;
        int height = 250;
        String filePath = "webapps/uploads/bKashQR_" + reference + ".png";  // Store in web directory

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, width, height);
            Path path = Paths.get(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            return "uploads/bKashQR_" + reference + ".png";
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
