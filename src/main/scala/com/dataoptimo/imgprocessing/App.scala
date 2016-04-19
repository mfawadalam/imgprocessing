package com.dataoptimo.imgprocessing

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.FSDataInputStream
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.io.BytesWritable
import org.apache.hadoop.io.SequenceFile
import org.apache.hadoop.io.IOUtils
import com.dataoptimo.imgprocessing.convert.Converter
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import com.dataoptimo.imgprocessing.format.WholeFileInputFormat
import org.opencv.core.Mat

object App  {
  
  def main(args: Array[String]){
	  	val confHadoop = new Configuration()     
      confHadoop.addResource(new Path("/users/fawadalam/Documents/cloudera_vm/conf/core-site.xml"))
      confHadoop.addResource(new Path("/users/fawadalam/Documents/cloudera_vm/conf/hdfs-site.xml"))
      /*
      val job = Job.getInstance(confHadoop)
	  	job.setInputFormatClass(classOf[WholeFileInputFormat]);
      job.setOutputFormatClass(classOf[SequenceFileOutputFormat]);
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(BytesWritable.class);
      job.setMapperClass(SequenceFileMapper.class); return job.waitForCompletion(true) ? 0 : 1;*/
    
      val conf = new SparkConf()
	  	conf.setMaster("local")
	  	conf.setAppName("ll")
      val sc = new SparkContext(conf)
      //val rdd = sc.newAPIHadoopFile[NullWritable,BytesWritable,WholeFileInputFormat]("/user/fawadalam/images/img_99697.jpg", classOf[WholeFileInputFormat], classOf[NullWritable], classOf[BytesWritable], confHadoop)
      val rdd = sc.newAPIHadoopFile("/user/fawadalam/images/img_99697.jpg", classOf[WholeFileInputFormat], classOf[NullWritable], classOf[BytesWritable], confHadoop)
	  	val mat = rdd.map(x=>OpenCVOps.imageToMat(x._2.getBytes))
	  	rdd.map(x=>OpenCVOps.imageToMat(x._2.getBytes)).map(x=>x.mat.cols).foreach(println)
	  	val x = mat.collect()
	  	println(x(0).mat.cols)
	  	
  }
  

  
}
