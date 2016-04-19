package com.dataoptimo.matserializer
import org.opencv.core.Mat
import org.opencv.core.CvType
import org.opencv.highgui.Highgui
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.opencv.core.Core
import org.opencv.features2d.FeatureDetector
import org.opencv.core.MatOfKeyPoint
import org.opencv.features2d.Features2d

case class MatSerializable (rows: Int, cols: Int, cvType: Int, bytes: Array[Byte])

object MatSerializable {
 
  
  def serialize(mat : Mat): MatSerializable={
    
   val size = mat.rows()*mat.cols()*mat.elemSize()
  // val cvType = mat.type()
   val byteArray = new Array[Byte](size.toInt)
   mat.get(0,0,byteArray)
   MatSerializable(mat.rows,mat.cols,CvType.CV_8UC3,byteArray) 
  }
  
  def deserialize(matSerializable: MatSerializable): Mat ={
    val mat = new Mat(matSerializable.rows,matSerializable.cols,matSerializable.cvType)
    mat.put(0,0,matSerializable.bytes)
    mat
  }
  
  /*def main(args: Array[String]){
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("my app")
    val sc = new SparkContext(conf)
    val m = Highgui.imread("C:\\Users\\alamm3\\Downloads\\imgs\\train\\c0\\img_609.jpg")
    println("rows: "+m.rows()+" cols: "+m.cols())
    val ser = serialize(m)
    val rdd = sc.parallelize(Array(ser))
    val z = rdd.collect().map(x=>deserialize(x))
    //val y = z(0)
    //Highgui.imwrite("C:\\Users\\alamm3\\Downloads\\imgs\\train\\c0\\img_34.jpg",y)
    val matKeyPoints = new MatOfKeyPoint()
    val detector = FeatureDetector.create(FeatureDetector.SIFT)
    detector.detect(m,matKeyPoints)
    val m2 = new Mat()
    Features2d.drawKeypoints(m,matKeyPoints,m2)
    Highgui.imwrite("C:\\Users\\alamm3\\Downloads\\imgs\\train\\c0\\img_609_1.jpg",m2)
    
    
  }*/
  
}