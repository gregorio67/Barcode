package dymn.blockchain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;


public class BarCodeUtil {

	
	private static final int BARCODE_DEFAULT_WIDTH = 400;
	private static final int BARCODE_DEFAULT_HEIGHT = 300;
	private static final String BARCODE_DEFAULT_IMAGE_FORMAT = "png";  
	
	
	/**
	 * 
	 *<pre>
	 * Create QR BarCode
	 *</pre>
	 * @param text String  contents of bar code
	 * @param outFile String output file name
	 * @throws Exception
	 */
	public static void createQRBarcode(String text, String outFile) throws Exception {
		createQRBarcode(text,outFile, BARCODE_DEFAULT_WIDTH, BARCODE_DEFAULT_HEIGHT, BARCODE_DEFAULT_IMAGE_FORMAT);
	}

	/**
	 * 
	 *<pre>
	 * Create QR BarCode
	 *</pre>
	 * @param text String  contents of bar code
	 * @param outFile outFile String output file name
	 * @param imageFormat String bar code image format
	 * @throws Exception
	 */
	public static void createQRBarcode(String text, String outFile, String imageFormat) throws Exception {
		createQRBarcode(text,outFile, BARCODE_DEFAULT_WIDTH, BARCODE_DEFAULT_HEIGHT, imageFormat);
		
	}

	/**
	 * 
	 *<pre>
	 * Create QR BarCode
	 *</pre>
	 * @param text text String  contents of bar code
	 * @param outFile outFile String output file name
	 * @param width int bar code width
	 * @param height int bar code height
	 * @throws Exception
	 */
	public static void createQRBarcode(String text, String outFile, int width, int height) throws Exception {
		createQRBarcode(text,outFile, width, height, BARCODE_DEFAULT_IMAGE_FORMAT);
		
	}
	
	public static void createQRBarcode(String text, String outFile, int width, int height, String imageFormat) throws Exception {

		if ("gif".equalsIgnoreCase(imageFormat) || "tiff".equalsIgnoreCase(imageFormat) || "jpeg".equalsIgnoreCase(imageFormat) || "png".equalsIgnoreCase(imageFormat)) {
			BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
			MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, new FileOutputStream(new File(outFile)));					
		}
		else {
			throw new RuntimeException("Barcode image type is not supported");
		}
		
	}
		
	public static String readQRBarcode(String inFile) throws Exception {
		InputStream barCodeInputStream = new FileInputStream(inFile);
		BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);

		LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Reader reader = new MultiFormatReader();
		Result result = reader.decode(bitmap);

		return result.getText();		
	}
	

	public static void createPDF417Barcode(String text, String outFile) throws Exception {
		createQRBarcode(text,outFile, BARCODE_DEFAULT_WIDTH, BARCODE_DEFAULT_HEIGHT, BARCODE_DEFAULT_IMAGE_FORMAT);
	}

	public static void createPDF417Barcode(String text, String outFile, String imageFormat) throws Exception {
		createQRBarcode(text,outFile, BARCODE_DEFAULT_WIDTH, BARCODE_DEFAULT_HEIGHT, imageFormat);
		
	}

	public static void createPDF417Barcode(String text, String outFile, int width, int height) throws Exception {
		createQRBarcode(text,outFile, width, height, BARCODE_DEFAULT_IMAGE_FORMAT);
		
	}
	
	public static void createPDF417Barcode(String text, String outFile, int width, int height, String imageFormat) throws Exception {

		if ("gif".equalsIgnoreCase(imageFormat) || "tiff".equalsIgnoreCase(imageFormat) || "jpeg".equalsIgnoreCase(imageFormat) || "png".equalsIgnoreCase(imageFormat)) {
			BitMatrix bitMatrix = new PDF417Writer().encode(text, BarcodeFormat.PDF_417, width, height);
			MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, new FileOutputStream(new File(outFile)));		
		}
		else {
			throw new RuntimeException("Barcode image type is not supported");
		}		
	}
	
	public static String readPDF417Barcode(String inFile) throws Exception {
		InputStream barCodeInputStream = new FileInputStream(inFile);
		BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);

		LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Reader reader = new MultiFormatReader();
		Result result = reader.decode(bitmap);

		return result.getText();		
	}
	
	
	public static void main(String args[]) throws Exception {
		String filename = "D:/temp/qr_test_barcode.png";
		String filename1 = "D:/temp/pdf417_test_barcode.png";
		createQRBarcode("This is qr bar code test", filename);
		System.out.println("Read Text :: " + readQRBarcode(filename));

		createPDF417Barcode("This is pdf 417 bar code test", filename1);
		System.out.println("Read Text :: " + readPDF417Barcode(filename1));

	}
}
