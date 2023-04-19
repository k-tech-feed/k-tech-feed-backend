resource "aws_route53_zone" "default" {
  name = var.domain
}

resource "aws_acm_certificate" "default" {
  provider = aws.virginia

  domain_name               = var.domain
  subject_alternative_names = ["*.${var.domain}"]
  validation_method         = "DNS"
}

resource "aws_route53_record" "default" {
  for_each = {
    for dvo in aws_acm_certificate.default.domain_validation_options : dvo.domain_name => {
      name   = dvo.resource_record_name
      record = dvo.resource_record_value
      type   = dvo.resource_record_type
    }
  }
  allow_overwrite = true
  name            = each.value.name
  records         = [each.value.record]
  ttl             = 60
  type            = each.value.type
  zone_id         = aws_route53_zone.default.zone_id
}

resource "aws_route53_record" "frontend" {
  allow_overwrite = true
  name            = var.domain
  records         = [var.frontend_ip]
  ttl             = 60
  type            = "A"
  zone_id         = aws_route53_zone.default.zone_id
}

output "acm_certificate_dns_validation_records" {
  description = "record which is used to validate acm certificate"
  value       = aws_acm_certificate.default.*.domain_validation_options
}
