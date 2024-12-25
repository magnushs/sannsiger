package com.devgenerate.sannsiger.service

import com.devgenerate.sannsiger.model.TabFile
import com.devgenerate.sannsiger.repository.TabFileRepository
import org.springframework.stereotype.Service

@Service
class TabFileService(private val tabFileRepository: TabFileRepository) {
    fun save(tabFile: TabFile): TabFile {
        return tabFileRepository.save(tabFile)
    }

    fun findAll(): List<TabFile> {
        return tabFileRepository.findAll()
    }

    fun findById(id: Long): TabFile {
        return tabFileRepository.findById(id).orElseThrow { IllegalArgumentException("Tab file not found") }
    }

    fun deleteById(id: Long) {
        tabFileRepository.deleteById(id)
    }
}