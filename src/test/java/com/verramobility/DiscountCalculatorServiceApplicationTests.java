package com.verramobility;

import com.verramobility.daoimpl.DiscountCalculationService;
import com.verramobility.dto.ComponentDto;
import com.verramobility.dto.ResponseDto;
import com.verramobility.service.DiscountCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@SpringBootTest
class DiscountCalculatorServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@InjectMocks
	private DiscountCalculatorService discountCalculatorService;

	@Mock
	private DiscountCalculationService discountCalculationService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testPrintReceipt() {

		List<ComponentDto> items = new ArrayList<>();

		ComponentDto book = new ComponentDto("book", 15.49, "books", false, 1);
		ComponentDto cloth = new ComponentDto("shirt", 18.89, "cloths", false, 1);
		ComponentDto drink = new ComponentDto("wine", 28.89, "drinks", true, 1);

		items.add(book);
		items.add(cloth);
		items.add(drink);

		when(discountCalculationService.calculateDiscount(book)).thenReturn(0.77);
		when(discountCalculationService.calculateDiscount(cloth)).thenReturn(3.78);
		when(discountCalculationService.calculateDiscount(drink)).thenReturn(7.22);
		when(discountCalculationService.round(anyDouble())).thenAnswer(invocation -> {
			Double arg = invocation.getArgument(0);
			return Math.round(arg * 100.0) / 100.0;
		});

		ResponseDto responseDto = discountCalculatorService.printReceipt(items);

		assertEquals(3, responseDto.getItems().size());
		assertEquals(14.72, responseDto.getItems().get(0).getPrice());
		assertEquals(15.11, responseDto.getItems().get(1).getPrice());
		assertEquals(21.67, responseDto.getItems().get(2).getPrice());
		assertEquals(51.5, responseDto.getTotal());
		assertEquals(11.77, responseDto.getTotalSaved());
	}

}
