package com.scarlxrd.agregadorinvestimentos.model.client.dto;

import java.util.List;

public record BrapiResponseDto(List<StockDto> results) {
}
