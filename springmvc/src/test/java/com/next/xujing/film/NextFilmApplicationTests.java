package com.next.xujing.film;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.next.xujing.film.dao.entity.NextUser;
import com.next.xujing.film.dao.mapper.NextUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class NextFilmApplicationTests {
	@Resource
	private NextUserMapper nextUserMapper;
	@Test
	public void contextLoads() {
	}

	@Test
	public void addUser(){
		NextUser nextUser = new NextUser();
		nextUser.setUserName("next学院");
		nextUser.setUserPwd("1213");
		int isSuccess = nextUserMapper.insert(nextUser);
		System.out.println("isSuccess="+(isSuccess==1?true:false));

	}
	@Test
	//修改
	public void updateUser(){
		//第一种更新方式
		/*NextUser nextUser = new NextUser();
		nextUser.setUuid(1);
		nextUser.setUserName("lili");
		nextUser.setUserPwd("16");
		int isSuccess = nextUserMapper.updateById(nextUser);
		System.out.println("isSuccess="+(isSuccess==1?true:false));*/
		//第二种根据条件进行更新
		NextUser nextUser = new NextUser();
		nextUser.setUserPwd("我很喜欢！");
		AbstractWrapper abstractWapper = new UpdateWrapper();
		abstractWapper.eq("user_name","next学院");
		int isSuccess = nextUserMapper.update(nextUser,abstractWapper);
		System.out.println("isSuccess="+(isSuccess==1?true:false));
	}
	@Test
	public void deleteUser(){
		int id = 6;
		int isSuccess = nextUserMapper.deleteById(id);
		System.out.println("isSuccess="+(isSuccess==1?true:false));


	}
	@Test
	//根据id查询
	public void queryById(){
		int id = 1;
		NextUser nextUser = nextUserMapper.selectById(id);
		System.out.println(nextUser);
	}
	//根据条件进行查询
	@Test
	public void mybatisQuery(){
		AbstractWrapper abstractWrapper = new QueryWrapper();
		abstractWrapper.eq("user_name","admin02");
		List list = nextUserMapper.selectList(abstractWrapper);
		list.forEach(System.out::println);

	}
	@Test
	//自定义查询
	public void defineSqlTest(){
		List<NextUser>  nextUsers = nextUserMapper.getUsers();
		nextUsers.forEach(System.out::println);
	}
	//分页
	@Test
	public void pageTest(){
		IPage<NextUser> iPage = new Page<>();
		//设置起始页
		iPage.setCurrent(1);
		iPage.setSize(2);
		IPage<NextUser> nextUserIPage = nextUserMapper.selectPage(iPage, null);//null是没有条件的分页查询
		System.out.println("nextUserIPage="+nextUserIPage.getTotal());
		System.out.println("nextUserIPage="+nextUserIPage.getRecords());//记录
	}

}
