package ru.se.web.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.se.web.model.Product;
import ru.se.web.repositories.ProductRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManageProductService {
    @Value("${aws.s3.bucket}")
    private String bucket;
    @Value("${aws.s3.product-folder}")
    private String folder;
    private final ProductRepository productRepository;
    private final AmazonS3 amazonS3;
    private static final Logger LOGGER= LoggerFactory.getLogger(ManageProductService.class);
    public ManageProductService(ProductRepository productRepository,AmazonS3 amazonS3) {
        this.productRepository = productRepository;
        this.amazonS3=amazonS3;
    }
    public List<Product> getAllProductAsc(){
        return productRepository.getAllProductAsc();
    }
    public void saveProduct(Product product,MultipartFile image){
        long count=productRepository.count();
        product.setProductCode(createProductCode(count+1));
        product.setObjectKey(product.getProductCode()+getExt(image.getOriginalFilename()));
        productRepository.addProduct(product);
        saveImage(image,product.getObjectKey());
    }
    public Product getProductByCode(String code){
        return  productRepository.findFirstByProductCode(code);
    }
    private String createProductCode(long count){
        return String.format("P%06d",count);
    }
    private String getExt(String filename){
        try{
            return filename.substring(filename.lastIndexOf("."));
        }catch(Exception ex){
            return ".jpg";
        }
    }
    private void saveImage(MultipartFile image,String obj){
        try{
            File conv=new File("ProductImage/"+obj);
            FileOutputStream fos=new FileOutputStream(conv);
            fos.write(image.getBytes());
            fos.close();
            PutObjectRequest putObjectRequest=new PutObjectRequest(bucket,folder+obj,conv);
            amazonS3.putObject(putObjectRequest);
        }catch(Exception e){
            LOGGER.info(e.toString());
        }
    }
    /*private void saveImage(MultipartFile image,String obj){
        try{
            File conv=new File("ProductImage/"+obj);
            FileOutputStream fos=new FileOutputStream(conv);
            fos.write(image.getBytes());
            fos.close();
        }catch (Exception ex){
            LOGGER.info(ex.toString());
        }
    }*/
}
