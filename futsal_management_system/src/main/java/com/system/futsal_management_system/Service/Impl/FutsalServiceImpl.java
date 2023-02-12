package com.system.futsal_management_system.Service.Impl;


import com.system.futsal_management_system.Pojo.FutsalPojo;
import com.system.futsal_management_system.Repo.FutsalRepo;
import com.system.futsal_management_system.Service.FutsalService;
import com.system.futsal_management_system.entity.Futsal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FutsalServiceImpl implements FutsalService {

    private final FutsalRepo futsalRepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/";


    @Override
    public FutsalPojo savefutsal(FutsalPojo futsalPojo) throws IOException {
        Futsal futsal = new Futsal();
        if (futsalPojo.getFid()!= null){
            futsal.setFut_salId(futsalPojo.getFid());
        }
        futsal.setFutsalname(futsalPojo.getFname());
        futsal.setFutsalprice(futsalPojo.getFprice());
        futsal.setFutsalcontact(futsalPojo.getFcontact());
        futsal.setFutsallocation(futsalPojo.getFlocation());
        futsal.setDescription(futsalPojo.getDescription());



        if(futsalPojo.getImage1()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, futsalPojo.getImage1().getOriginalFilename());
            fileNames.append(futsalPojo.getImage1().getOriginalFilename());
            Files.write(fileNameAndPath, futsalPojo.getImage1().getBytes());

            futsal.setFutsalimage1(futsalPojo.getImage1().getOriginalFilename());
        }
        if(futsalPojo.getImage2()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, futsalPojo.getImage2().getOriginalFilename());
            fileNames.append(futsalPojo.getImage2().getOriginalFilename());
            Files.write(fileNameAndPath, futsalPojo.getImage2().getBytes());

            futsal.setFutsalimage2(futsalPojo.getImage2().getOriginalFilename());
        }
        if(futsalPojo.getImage()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, futsalPojo.getImage().getOriginalFilename());
            fileNames.append(futsalPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, futsalPojo.getImage().getBytes());

            futsal.setFutsalimage(futsalPojo.getImage().getOriginalFilename());
        }
        futsalRepo.save(futsal);
        return new FutsalPojo(futsal);
    }

    @Override
    public Futsal fetchById(Integer id) {
        Futsal futsal= futsalRepo.findById(id).orElseThrow(()-> new RuntimeException("Couldnot find"));
        futsal = Futsal.builder()
                .fut_salId(futsal.getFut_salId())
                .imageBase64(getImageBase64(futsal.getFutsalimage()))
                .image1Base64(getImageBase64(futsal.getFutsalimage1()))
                .image2Base64(getImageBase64(futsal.getFutsalimage2()))
                .futsalname(futsal.getFutsalname())
                .futsalcontact(futsal.getFutsalcontact())
                .futsalprice(futsal.getFutsalprice())
                . futsallocation(futsal.getFutsallocation())
                .Description(futsal.getDescription())
                .build();
        return futsal;
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/images/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public List<Futsal> fetchAll() {
        return futsalRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        futsalRepo.deleteById(id);
    }

}
