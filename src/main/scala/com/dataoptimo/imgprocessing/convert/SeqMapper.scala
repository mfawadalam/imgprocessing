package com.dataoptimo.imgprocessing.convert
import org.apache.hadoop.mapreduce.Mapper
import org.apache.hadoop.io.{Text,NullWritable,BytesWritable}
import org.apache.hadoop.mapred.FileSplit
  

class SeqMapper extends Mapper[NullWritable,BytesWritable,Text,BytesWritable] {
  
        private var fileName = new Text("");
        
        override def setup(context: Mapper[NullWritable,BytesWritable,Text,BytesWritable]#Context){
          val split = context.getInputSplit;
          val path = split.asInstanceOf[FileSplit].getPath
          fileName = new Text(path.toString)
        
        }
  
        override def map(key: NullWritable,value: BytesWritable,context: Mapper[NullWritable,BytesWritable,Text,BytesWritable]#Context){
          context.write(fileName,value);  
          
        }
  
}