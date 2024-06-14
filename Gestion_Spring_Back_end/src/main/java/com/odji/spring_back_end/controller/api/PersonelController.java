package com.odji.spring_back_end.controller.api;

import com.odji.spring_back_end.dto.PersonelDto;
import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/api/personel")
public class PersonelController {
//    @Autowired
//    private PersonelService psersonelService;
//
//    @PostMapping("/save")
//    public ResponseEntity<PersonelDto> createProduit(@RequestBody PersonelDto PersonelDto) {
//        PersonelDto createdPersonel = personelService.save(PersonelDto);
//        return new ResponseEntity<>(createdPersonel, HttpStatus.CREATED);
//    }
//
//    public List<ArticleDto> findAll() {
//        return articleService.findAll();
//    }
//    public ArticleDto save(ArticleDto dto) {
//        return articleService.save(dto);
//    }
//}
}