package com.lunsir.wx.entity;

import lombok.Data;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-10-11 20:35
 */
@Data
public class AiResponse {
    String model;
    List<AiMessage> messages;
}
