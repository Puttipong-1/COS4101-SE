package ru.se.web.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config {
    @Value("${aws.s3.access-key}")
    private String accessKey;
    @Value("${aws.s3.secret-key}")
    private String secretKey;
    @Value("${aws.s3.bucket}")
    private String bucket;
    @Value("${aws.s3.region}")
    private String region;
    @Bean
    public AmazonS3 getAmazonS3Client(){
       final BasicAWSCredentials basicAWSCredentials=new BasicAWSCredentials(accessKey,secretKey);
       return AmazonS3ClientBuilder
               .standard()
               .withRegion(Regions.AP_SOUTHEAST_1)
               .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
               .build();
    }
}
