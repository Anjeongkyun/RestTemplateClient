package com.daon.host.web.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.daon.host.web.vo.LoginVo;
import com.daon.host.web.vo.ParkVo;
<<<<<<< .mine

=======
import com.daon.host.web.vo.ParkVo.Table;
>>>>>>> .theirs

@Component
@Mapper
public interface ParkMapper {
<<<<<<< .mine

=======

>>>>>>> .theirs
	List<ParkVo> getList();
	
	
	
}
