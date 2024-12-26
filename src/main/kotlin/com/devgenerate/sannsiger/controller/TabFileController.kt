package com.devgenerate.sannsiger.controller

import com.devgenerate.sannsiger.model.TabFile
import com.devgenerate.sannsiger.service.TabFileService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

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

    @PostMapping("/upload")
    fun uploadfile(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("title") title: String): TabFile {
        val uploadDir = Paths.get("tabs")
        if (!Files.exists(uploadDir)) Files.createDirectories(uploadDir)

        val filePath = uploadDir.resolve(file.originalFilename!!)
        file.inputStream.use { Files.copy(it, filePath, StandardCopyOption.REPLACE_EXISTING) }

        val fileUrl = filePath.toString()
        val tabFile = TabFile(title = file.originalFilename!!, fileUrl = fileUrl)

        return tabFileService.save(tabFile)
    }

    @GetMapping("/download/{id}")
    fun downloadFile(@PathVariable id: Long, response: HttpServletResponse) {
        val tabFile = tabFileService.findById(id)

        val file = Paths.get(tabFile.fileUrl).toFile()
        response.contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE
        response.setHeader("Content-Disposition", "attachment; filename=${tabFile.title}")
        response.outputStream.use { Files.copy(file.toPath(), it) }
    }

}