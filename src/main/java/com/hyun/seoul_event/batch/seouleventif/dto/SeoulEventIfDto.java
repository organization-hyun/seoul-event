package com.hyun.seoul_event.batch.seouleventif.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.hyun.seoul_event.batch.seouleventif.SeoulEventIf;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeoulEventIfDto {
    private String serviceType;
    private String serviceId;
    private String category;
    private String subcategory;
    private String serviceStatus;
    private String serviceName;
    private String paymentMethod;
    private String locationName;
    private String serviceTarget;
    private String url;
    private BigDecimal locationX;
    private BigDecimal locationY;
    private Instant serviceStart;
    private Instant serviceEnd;
    private Instant registrationStart;
    private Instant registrationEnd;
    private String regionName;
    private String imagePath;
    private String details;
    private String phoneNumber;
    private LocalTime serviceUseStart;
    private LocalTime serviceUseEnd;
    private String cancellationPolicyInfo;
    private Integer cancellationDeadline;

    /**
     * JsonNode를 SeoulEventIfDTO로 변환하는 메서드
     */
    public static SeoulEventIfDto fromJson(JsonNode jsonNode) {
        return SeoulEventIfDto.builder()
                .serviceType(jsonNode.path("GUBUN").asText())
                .serviceId(jsonNode.path("SVCID").asText())
                .category(jsonNode.path("MAXCLASSNM").asText())
                .subcategory(jsonNode.path("MINCLASSNM").asText())
                .serviceStatus(jsonNode.path("SVCSTATNM").asText())
                .serviceName(jsonNode.path("SVCNM").asText())
                .paymentMethod(jsonNode.path("PAYATNM").asText())
                .locationName(jsonNode.path("PLACENM").asText())
                .serviceTarget(jsonNode.path("USETGTINFO").asText())
                .url(jsonNode.path("SVCURL").asText())
                .locationX(jsonNode.has("X") ? new BigDecimal(jsonNode.path("X").asText()) : null)
                .locationY(jsonNode.has("Y") ? new BigDecimal(jsonNode.path("Y").asText()) : null)
                .serviceStart(parseInstant(jsonNode.path("SVCOPNBGNDT").asText()))
                .serviceEnd(parseInstant(jsonNode.path("SVCOPNENDDT").asText()))
                .registrationStart(parseInstant(jsonNode.path("RCPTBGNDT").asText()))
                .registrationEnd(parseInstant(jsonNode.path("RCPTENDDT").asText()))
                .regionName(jsonNode.path("AREANM").asText())
                .imagePath(jsonNode.path("IMGURL").asText())
                .details(jsonNode.path("DTLCONT").asText())
                .phoneNumber(jsonNode.path("TELNO").asText())
                .serviceUseStart(parseLocalTime(jsonNode.path("V_MIN").asText()))
                .serviceUseEnd(parseLocalTime(jsonNode.path("V_MAX").asText()))
                .cancellationPolicyInfo(jsonNode.path("REVSTDDAYNM").asText())
                .cancellationDeadline(jsonNode.path("REVSTDDAY").asInt())
                .build();
    }

    /**
     * DTO를 Entity로 변환하는 메서드
     */
    public SeoulEventIf toEntity() {
        return SeoulEventIf.builder()
                .serviceType(this.serviceType)
                .serviceId(this.serviceId)
                .category(this.category)
                .subcategory(this.subcategory)
                .serviceStatus(this.serviceStatus)
                .serviceName(this.serviceName)
                .paymentMethod(this.paymentMethod)
                .locationName(this.locationName)
                .serviceTarget(this.serviceTarget)
                .url(this.url)
                .locationX(this.locationX)
                .locationY(this.locationY)
                .serviceStart(this.serviceStart)
                .serviceEnd(this.serviceEnd)
                .registrationStart(this.registrationStart)
                .registrationEnd(this.registrationEnd)
                .regionName(this.regionName)
                .imagePath(this.imagePath)
                .details(this.details)
                .phoneNumber(this.phoneNumber)
                .serviceUseStart(this.serviceUseStart)
                .serviceUseEnd(this.serviceUseEnd)
                .cancellationPolicyInfo(this.cancellationPolicyInfo)
                .cancellationDeadline(this.cancellationDeadline)
                .build();
    }

    private static Instant parseInstant(String dateTime) {
        try {
            return Instant.parse(dateTime.replace(" ", "T"));
        } catch (Exception e) {
            return null;
        }
    }

    private static LocalTime parseLocalTime(String time) {
        try {
            return LocalTime.parse(time);
        } catch (Exception e) {
            return null;
        }
    }
}
