package com.devgenerate.sannsiger.controller

import com.devgenerate.sannsiger.model.TabFile
import com.devgenerate.sannsiger.service.TabFileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("/api/tab-file")
class TabFileController(private val tabFileService: TabFileService) {

    @GetMapping
    fun getAllTabFiles() = tabFileService.findAll()

    @GetMapping("/{id}")
    fun getTabFileById(id: Long) = tabFileService.findById(id)

    @PostMapping
    fun saveTabFile(@RequestBody tabFile: TabFile) {
        tabFileService.save(tabFile)
    }

    @DeleteMapping("/{id}")
    fun deleteTabFileById(id: Long) {
        tabFileService.deleteById(id)
    }

}