package com.uni.population.nationpopulation.Controller;

import com.uni.population.nationpopulation.model.dao.PopulationDao;
import com.uni.population.nationpopulation.model.dto.PopulationDto;
import com.uni.population.nationpopulation.model.service.PopulationService;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/nation")
public class NationController {

    private final ResourceLoader resourceLoader;

    private final PopulationService populationService;
    private final MessageSource messageSource;

    private final PopulationDao populationDao;


    public NationController(PopulationService populationService, MessageSource messageSource, ResourceLoader resourceLoader, PopulationDao populationDao) {
        this.populationService = populationService;
        this.messageSource = messageSource;
        this.resourceLoader = resourceLoader;
        this.populationDao = populationDao;
    }


    @GetMapping("/populationlist")
    public ModelAndView findAllNation(ModelAndView mv) {
        List<PopulationDto> populationList = populationService.findAllNation();
        populationList.forEach(population -> System.out.println("population=" + population));

        mv.addObject("populationList", populationList);

        mv.setViewName("nation/populationlist");
//        view어느 풀더에 어느 파일

        return mv;
    }


    @GetMapping("/nationinsert")
    public void registPage() {
    }

    @PostMapping(value = "/nationinsert", produces = MediaType.IMAGE_PNG_VALUE)
    public ModelAndView registNation(@RequestParam("image") MultipartFile imageFile, @Validated PopulationDto populationDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();


        try {
            // 处理图像文件上传

            if (imageFile != null && !imageFile.isEmpty()) {
                String imageFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                String uploadDir = resourceLoader.getResource("classpath:/static/image").getFile().getAbsolutePath();
                File file = new File(uploadDir + File.separator + imageFileName);
                imageFile.transferTo(file);

                populationDto.setImage(imageFileName);

                // 插入数据到数据库
                int result = populationDao.registNation(populationDto);

                if (result > 0) {
                    modelAndView.setViewName("redirect:/nation/populationlist");
                    modelAndView.addObject("message", "등록 성공");
                } else {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("error", "등록 실패");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "등록 실패");
        }

        return modelAndView;
    }

    @GetMapping("/nationdetail/{nationno}")
    public String findNationByCode(@PathVariable int nationno, Model model) {

        PopulationDto populationDto = populationService.findNationByCode(nationno);


        model.addAttribute("populationDto", populationDto);


        return "nation/nationdetail";
    }

    @DeleteMapping("/nationdelete/{nationno}")
    public ResponseEntity<String> deletePopulation(@PathVariable int nationno) {
        // 执行删除操作
        populationService.deletePopulation(nationno);
        return ResponseEntity.ok("Population deleted successfully");
    }

    @GetMapping("/nationupdateform/{nationno}")
    public ModelAndView updateForm(@PathVariable("nationno") int nationno, ModelAndView mv) {
        mv.addObject("populationDto", populationService.findNationByCode(nationno))
                .setViewName("nation/nationupdate");

        return mv;
    }

    @PostMapping(value = "/nationupdate", produces = MediaType.IMAGE_PNG_VALUE)
    public ModelAndView updatePopulation(
            @RequestParam(value = "image", required = false) MultipartFile imageFile,
            @Validated PopulationDto populationDto,
            BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            // 处理图像文件上传
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                String uploadDir = resourceLoader.getResource("classpath:/static/image").getFile().getAbsolutePath();
                File file = new File(uploadDir + File.separator + imageFileName);
                imageFile.transferTo(file);

                // 删除旧照片
                String oldImageFileName = populationDto.getImage();
                if (oldImageFileName != null && !oldImageFileName.isEmpty()) {
                    File oldFile = new File(uploadDir + File.separator + oldImageFileName);
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                }

                populationDto.setImage(imageFileName);
            }

            // 检查是否有新图片上传，如果没有，保留原始图片
            if (imageFile == null || imageFile.isEmpty()) {
                // 获取原始图片名称并设置回 populationDto
                PopulationDto originalDto = populationService.findNationByCode(populationDto.getNationno());
                if (originalDto != null) {
                    populationDto.setImage(originalDto.getImage());
                }
            }

            // 更新数据到数据库
            int result = populationDao.updatePopulation(populationDto);

            if (result > 0) {
                modelAndView.setViewName("redirect:/nation/populationlist");
                modelAndView.addObject("message", "업데이트 성공");
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("error", "업데이트 실패");
            }
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "업데이트 실패");
        }

        // 将 populationDto 放入模型中
        modelAndView.addObject("population", populationDto);

        return modelAndView;
    }
}