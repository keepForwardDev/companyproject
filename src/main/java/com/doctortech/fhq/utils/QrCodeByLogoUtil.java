package com.doctortech.fhq.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.lang3.StringUtils;

import com.google.zxing.NotFoundException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeByLogoUtil {

    public static final String QRCODE_DEFAULT_CHARSET = "UTF-8";

    public static final int QRCODE_DEFAULT_HEIGHT = 150;

    public static final int QRCODE_DEFAULT_WIDTH = 150;

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static void main(String[] args) throws IOException, NotFoundException{
        String data = "http://www.baidu.com";
        File logoFile = new File("logo.png");
        BufferedImage image = QrCodeByLogoUtil.createQRCodeWithLogo(data, logoFile);
        ImageIO.write(image, "png", new File("result7.png"));
       // ImageIO.write(image, "png", response.getOutputStream());
        System.out.println("done");
    }

    
    
    /**
     * 生成二维码图片 不存储 直接以流的形式输出到页面
     * @param content 地址url
     * @param logoFile logo文件File
     * @param response
     * @param Width 二维码宽度
     * @param Height 二维码高度
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void encodeQrcode(String url,File logoFile,HttpServletResponse response,int Width ,int Height){
       
        try {
        	 if(StringUtils.isBlank(url))
             return;
             BufferedImage image = QrCodeByLogoUtil.createQRCodeWithLogo(url,Width,Height, logoFile);
			 ImageIO.write(image, "png", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    
    
    /**
     * 生成二维码图片 不存储 直接以流的形式输出到页面
     * @param content
     * @param response
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void encodeQrcodeNoLogo(String content,HttpServletResponse response,int Width ,int Hieght){
        if(StringUtils.isBlank(content))
            return;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 0);   //设置白边
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, Width, Hieght,hints);
            BufferedImage image = toBufferedImageMatrix(bitMatrix);
            //输出二维码图片流
            try {
                ImageIO.write(image, "png", response.getOutputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (WriterException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }         
    }
    
    private static BufferedImage toBufferedImageMatrix(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
          for (int y = 0; y < height; y++) {
            image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
          }
        }
        return image;
   }    
    
    
    
    /**
     * Create qrcode with default settings
     *
     * @author stefli
     * @param data
     * @return
     */
    public static BufferedImage createQRCode(String data) {
        return createQRCode(data, QRCODE_DEFAULT_WIDTH, QRCODE_DEFAULT_HEIGHT);
    }

    /**
     * Create qrcode with default charset
     *
     * @author stefli
     * @param data
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage createQRCode(String data, int width, int height) {
        return createQRCode(data, QRCODE_DEFAULT_CHARSET, width, height);
    }

    /**
     * Create qrcode with specified charset
     *
     * @author stefli
     * @param data
     * @param charset
     * @param width
     * @param height
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static BufferedImage createQRCode(String data, String charset, int width, int height) {
        Map hint = new HashMap();
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.CHARACTER_SET, charset);

        return createQRCode(data, charset, hint, width, height);
    }

    /**
     * Create qrcode with specified hint
     *
     * @author stefli
     * @param data
     * @param charset
     * @param hint
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage createQRCode(String data, String charset, Map<EncodeHintType, ?> hint, int width,
            int height) {
        BitMatrix matrix;
        try {
            matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE,
                    width, height, hint);
          //  matrix = deleteWhite(matrix);//删除白边
            return toBufferedImage(matrix);
        } catch (WriterException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    
    /**
     * 删除白边
     * */
    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }
    
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
    /**
     * Create qrcode with default settings and logo
     *
     * @author stefli
     * @param data
     * @param logoFile
     * @return
     */
    public static BufferedImage createQRCodeWithLogo(String data, File logoFile) {
        return createQRCodeWithLogo(data, QRCODE_DEFAULT_WIDTH, QRCODE_DEFAULT_HEIGHT, logoFile);
    }

    /**
     * Create qrcode with default charset and logo
     *
     * @author stefli
     * @param data
     * @param width
     * @param height
     * @param logoFile
     * @return
     */
    public static BufferedImage createQRCodeWithLogo(String data, int width, int height, File logoFile) {
        return createQRCodeWithLogo(data, QRCODE_DEFAULT_CHARSET, width, height, logoFile);
    }

    /**
     * Create qrcode with specified charset and logo
     *
     * @author stefli
     * @param data
     * @param charset
     * @param width
     * @param height
     * @param logoFile
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static BufferedImage createQRCodeWithLogo(String data, String charset, int width, int height, File logoFile) {
        Map hint = new HashMap();
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.CHARACTER_SET, charset);
        hint.put(EncodeHintType.MARGIN, 0);   //设置白边
        return createQRCodeWithLogo(data, charset, hint, width, height, logoFile);
    }

    /**
     * Create qrcode with specified hint and logo
     *
     * @author stefli
     * @param data
     * @param charset
     * @param hint
     * @param width
     * @param height
     * @param logoFile
     * @return
     */
    public static BufferedImage createQRCodeWithLogo(String data, String charset, Map<EncodeHintType, ?> hint,
            int width, int height, File logoFile) {
        try {
            BufferedImage qrcode = createQRCode(data, charset, hint, width, height);
            BufferedImage logo = ImageIO.read(logoFile);
            int deltaHeight = height - logo.getHeight();
            int deltaWidth = width - logo.getWidth();

            BufferedImage combined = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) combined.getGraphics();
            g.drawImage(qrcode, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g.drawImage(logo, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
        
            return combined;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Return base64 for image
     *
     * @author stefli
     * @param image
     * @return
     */
    public static String getImageBase64String(BufferedImage image) {
        String result = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            OutputStream b64 = new Base64OutputStream(os);
            ImageIO.write(image, "png", b64);
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Decode the base64Image data to image
     *
     * @author stefli
     * @param base64ImageString
     * @param file
     */
    public static void convertBase64StringToImage(String base64ImageString, File file) {
        FileOutputStream os;
        try {
            Base64 d = new Base64();
            byte[] bs = d.decode(base64ImageString);
            os = new FileOutputStream(file.getAbsolutePath());
            os.write(bs);
            os.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


}