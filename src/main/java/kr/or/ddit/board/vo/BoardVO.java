package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.attatch.vo.AttatchFileGroupVO;
import kr.or.ddit.attatch.vo.AttatchFileVO;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

@Data
@EqualsAndHashCode(of="boNo")
public class BoardVO implements Serializable{
	private int rnum;
	
	@NotNull(groups = {UpdateGroup.class, DeleteGroup.class})
	private Integer boNo;
	@NotBlank
	private String boTitle;
	@NotBlank
	private String boWriter;
	@NotBlank
	private String boIp;
	private String boMail;
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	@JsonIgnore
	private transient String boPass;
	@Exclude
	private String boContent;
	@DateTimeFormat(iso = ISO.DATE_TIME) // parsing 설정 String -> LocalDateTime
//	@JsonFormat(shape = Shape.STRING) // formatting 설정 LocalDateTime -> String
	private LocalDateTime boDate;
	private Integer boHit;
	
	private MultipartFile[] boFiles;
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles==null || boFiles.length==0) return;
		this.boFiles = boFiles;
		this.atchFileGroup = new AttatchFileGroupVO();
		atchFileGroup.setAtchFileList(
			Arrays.stream(boFiles)
					.filter((mf)->!mf.isEmpty())
					.map((mf)->new AttatchFileVO(mf))
					.collect(Collectors.toList())
		);
	}
	
	private AttatchFileGroupVO atchFileGroup;
	
	private Integer boAtch;
	
	private int atchCount;
	
	private MultipartFile[] addFiles;
	public void setAddFiles(MultipartFile[] addFiles) {
		if(addFiles==null || addFiles.length==0) return;
		this.addFiles = addFiles;
		this.addFileGroup = new AttatchFileGroupVO();
		addFileGroup.setAtchFileList(
			Arrays.stream(addFiles)
					.filter((mf)->!mf.isEmpty())
					.map((mf)->new AttatchFileVO(mf))
					.collect(Collectors.toList())
		);
	}
	
	private AttatchFileGroupVO addFileGroup;
	
	private AttatchFileGroupVO delFileGroup;
}




















