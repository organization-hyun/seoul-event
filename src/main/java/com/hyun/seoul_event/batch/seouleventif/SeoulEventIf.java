package com.hyun.seoul_event.batch.seouleventif;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "seoul_event_if")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeoulEventIf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_type", nullable = false)
    private String serviceType; // 서비스구분

    @Column(name = "service_id", nullable = false)
    private String serviceId; // 서비스ID

    @Column(name = "category")
    private String category; // 대분류명

    @Column(name = "subcategory")
    private String subcategory; // 소분류명

    @Column(name = "service_status")
    private String serviceStatus; // 서비스상태

    @Column(name = "service_name")
    private String serviceName; // 서비스명

    @Column(name = "payment_method")
    private String paymentMethod; // 결제방법

    @Column(name = "location_name")
    private String locationName; // 장소명

    @Column(name = "service_target")
    private String serviceTarget; // 서비스대상

    @Column(name = "url")
    private String url; // 바로가기URL

    @Column(name = "location_x", precision = 10, scale = 7)
    private BigDecimal locationX; // 장소X좌표

    @Column(name = "location_y", precision = 10, scale = 7)
    private BigDecimal locationY; // 장소Y좌표

    @Column(name = "service_start")
    private Instant serviceStart; // 서비스개시 시작일시

    @Column(name = "service_end")
    private Instant serviceEnd; // 서비스개시 종료일시

    @Column(name = "registration_start")
    private Instant registrationStart; // 접수시작일시

    @Column(name = "registration_end")
    private Instant registrationEnd; // 접수종료일시

    @Column(name = "region_name")
    private String regionName; // 지역명

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "details", columnDefinition = "text")
    private String details;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber; // 전화번호

    @Column(name = "service_use_start")
    private LocalTime serviceUseStart; // 서비스이용 시작시간

    @Column(name = "service_use_end")
    private LocalTime serviceUseEnd; // 서비스이용 종료시간

    @Column(name = "cancellation_policy_info")
    private String cancellationPolicyInfo; // 취소기간 기준정보

    @Column(name = "cancellation_deadline")
    private Integer cancellationDeadline; // 취소기간 기준일까지
    /**
     * 빌더에서 `id` 필드 제외
     */
    public static class SeoulEventIfBuilder {
        private Long id; // id는 빌더에서 제외
    }
}
