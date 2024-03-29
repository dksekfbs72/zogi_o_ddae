package com.zerobase.leisure.domain.dto;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeisureDto {
	private Long id;
	private Long sellerId;

	private String name;
	private String addr;
	private Integer price;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;

	public static LeisureDto from(Leisure leisure) {
		return LeisureDto.builder()
			.id(leisure.getId())
			.sellerId(leisure.getSellerId())
			.name(leisure.getName())
			.addr(leisure.getAddr())
			.price(leisure.getPrice())
			.maxPerson(leisure.getMaxPerson())
			.minPerson(leisure.getMinPerson())
			.lat(leisure.getLat())
			.lon(leisure.getLon())
			.build();
	}
}
