package com.devgenerate.sannsiger.repository

import com.devgenerate.sannsiger.model.TabFile
import org.springframework.data.jpa.repository.JpaRepository

interface TabFileRepository: JpaRepository<TabFile, Long>
