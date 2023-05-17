<h1 align="center">K Tech Feed - Backend</h1>
<p align="center">
  <img src="https://github.com/k-tech-feed/k-tech-feed-backend/assets/25472942/41045aa9-fc91-4497-967b-5d8bf3761f66" width="120px" alt="logo"/>
</p>

> K Tech Feed는 국내 IT 기업들의 기술 블로그 아티클을 수집하여 피드 형태로 제공하는 웹 서비스입니다. 여러 출처의 기술 블로그 게시물을 한 서비스 내에서 쉽게 조회하고 검색할 수 있습니다.

## 주요 기술적 특징

- 확장성을 고려하여 `Hexagonal Architecture`를 적용했습니다.
- 선언형 IaC(Infrastructure as Code)인 `Terraform`을 사용하여 AWS에 인프라를 구성하고 애플리케이션을 배포했습니다.
- OpenAI Chat API와 RSS 피드를 사용하여 아티클 수집 및 키워드 추출을 구현했습니다.

## 🛠️ 기술 스택

### API Server

- `Java 17 (Amazon Corretto 17.0.6)`
- `Spring Boot (3.0.5)`
- `Spring MVC`
- `Spring Data JPA`
- `QueryDSL (5.0.0)`
- `H2 (2.1.214)`
- `MySQL (8.0.32)`

### Article Crawler

- `Python (3.10)`
- `Poetry (1.4.2)`
- `openai`
- `feedparser`
- `beautifulsoup4`
- `apscheduler`

## Infra

- `Terraform (1.4.2)`

## 🏛️ 프로젝트 아키텍처

![](https://github.com/k-tech-feed/k-tech-feed-backend/assets/25472942/9657bb3f-f3f0-4bb2-92df-f5bb6741097e)
