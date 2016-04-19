package com.dataoptimo.imgprocessing.convert
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.io.Text
import org.apache.hadoop.io.BytesWritable
import org.apache.hadoop.fs.{Path, FileSystem}
import org.apache.hadoop.io.IOUtils
import org.apache.hadoop.io.SequenceFile
import java.io.IOException
import java.lang.IllegalArgumentException

class Converter(conf: Configuration) {
  
  def imageToSequence(srcPath: String, dstPath: String){
    try {
    val fs = FileSystem.get(conf);
    val inPath = new Path(srcPath);
    val outPath = new Path(dstPath);
    val key = new Text();
    val value = new BytesWritable();
    val in = fs.open(inPath);
    val buffer = new Array[Byte](in.available())
    in.read(buffer);
    var writer = SequenceFile.createWriter(fs, conf, outPath, key.getClass(),value.getClass());
    writer.append(new Text(inPath.getName()), new BytesWritable(buffer));
    IOUtils.closeStream(writer);
    }
    catch {
      case io: IOException => println(io.getMessage)
      case illegalArgument: IllegalArgumentException => println(illegalArgument.getMessage)
    }
    
    
    
  }
}