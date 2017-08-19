package com.ayouris.tawassol.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Configuration
@Profile({"dev", "prod"})
@Component
public class PropertiesHolders {

	
    @Value("${upload.folder}")
    private String uploadPath;
    
    @Value("${upload.PieceRegfolder}")
    private String uploadPathPieceReglement;

	public void setUploadPathPieceReglement(String uploadPathPieceReglement) {
		this.uploadPathPieceReglement = uploadPathPieceReglement;
	}

    
}
