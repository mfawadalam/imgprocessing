package com.dataoptimo.imgprocessing
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.FSDataInputStream
import org.apache.hadoop.io.Text
import org.apache.hadoop.io.BytesWritable
import org.apache.hadoop.io.SequenceFile
import org.apache.hadoop.io.IOUtils


object App  {
  
  def main(args: Array[String]){
	  	val confHadoop = new Configuration();     
        //confHadoop.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
        //confHadoop.addResource(new Path("/etc/hadoop/conf/hdfs-site.xml"));   
        val fs = FileSystem.get(confHadoop);
        val inPath = new Path("test/test.jpg");
        val outPath = new Path("test/11.jpg");
      
        val key = new Text();
        val value = new BytesWritable();
       // val writer = null;
        
            var in = fs.open(inPath);
            var buffer = new Array[Byte](in.available())
            in.read(buffer);
            var writer = SequenceFile.createWriter(fs, confHadoop, outPath, key.getClass(),value.getClass());
            writer.append(new Text(inPath.getName()), new BytesWritable(buffer));
       
            IOUtils.closeStream(writer);
            System.out.println("last line of the code....!!!!!!!!!!");
        
  
	  
    
  }
  
}
