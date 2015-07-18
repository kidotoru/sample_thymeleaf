package net.treewoods.sample.thymeleaf.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

	private static final List<Item> goodsList = new ArrayList<>();

	static {
		Item goods; 
		goods = new Item();
		goods.setCode("12345678");
		goods.setName("商品名０１");
		goods.setPrice("99999999");
		goods.setStartDate(new Date());
		goodsList.add(goods);
		
		goods = new Item();
		goods.setCode("10000000");
		goods.setName("商品名０２");
		goods.setPrice("99999999");
		goods.setStartDate(new Date());
		goodsList.add(goods);
		
		goods = new Item();
		goods.setCode("12345678");
		goods.setName("商品名０３");
		goods.setPrice("99999999");
		goods.setStartDate(new Date());
		goodsList.add(goods);		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		String message = "  [Spring + thymeleafサンプル]: " + formattedDate + ". ";
		model.addAttribute("thymeleaf", message );

		return "hello";
	}
	@RequestMapping(value = "/goods_list.html", method = RequestMethod.GET)
	public String goodsList(
			@RequestParam(value = "goods_code", required = false) String goodsCode,
			Locale locale, Model model) {

		List<Item> result = new ArrayList<>();
		
		if ( goodsCode != null && !goodsCode.isEmpty()){
			for( Item item : goodsList) {
				if ( item.getCode().equals(goodsCode)) {
					result.add(item);
				}
			}
		} else {
			result = goodsList;
		}
		


		model.addAttribute("goods_code", goodsCode);
		model.addAttribute("items", result);

		return "goods_list";
	}
	
	
}
