package com.dataoptimo.imgprocessing
import org.opencv.core.Mat
import javax.imageio.ImageIO
import java.io.ByteArrayInputStream
import java.awt.image.DataBufferByte
import org.opencv.core.CvType
import org.opencv.core.Core
import org.opencv.features2d.FeatureDetector
import org.opencv.core.MatOfKeyPoint
import org.opencv.features2d.Features2d
import org.opencv.highgui.Highgui
import java.awt.image.BufferedImage
import java.io.File



object OpenCVOps {
  
  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  
  def imageToMat(byteArray: Array[Byte]): MatSer ={
      val bufferedImage = ImageIO.read(new ByteArrayInputStream(byteArray))
      val mat = new Mat(bufferedImage.getHeight(), bufferedImage.getWidth(), CvType.CV_8UC3);
      val data = bufferedImage.getRaster().getDataBuffer.asInstanceOf[DataBufferByte].getData();
      mat.put(0, 0, data);
      new MatSer(mat);
  }
  
  def matToImage(mat: Mat,file: String): Boolean ={
    
      val imageType = BufferedImage.TYPE_3BYTE_BGR;
      val image = new BufferedImage(mat.cols(),mat.rows(), imageType);
      val x = image.getRaster.getDataBuffer.asInstanceOf[DataBufferByte].getData
      mat.get(0,0,x)
      val fileName = new File("file")
      ImageIO.write(image, "jpg", fileName)
    
  }
  
  def detectFeatures(mat: Mat)
  {
    val featureDetector = FeatureDetector.create(FeatureDetector.SIFT)
    val matKeyPoint = new MatOfKeyPoint()
    featureDetector.detect(mat,matKeyPoint)
    println(mat.get(0, 0))
    println(matKeyPoint.toList())
    //writeToImage(mat,matKeyPoint)
    
  }
  
  def writeToImage(mat: Mat, matKeyPoint: MatOfKeyPoint){
    val outImage = new Mat()
    Features2d.drawKeypoints(mat, matKeyPoint, outImage)
    Highgui.imwrite("myfile.jpg",outImage)
    
  }
  
}
  
  