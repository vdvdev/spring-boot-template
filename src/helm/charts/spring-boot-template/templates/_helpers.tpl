{{/* vim: set filetype=mustache: */}}
{{/*
Expand the name of the chart.
*/}}
{{- define "spbtemplate.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "spbtemplate.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := default .Chart.Name .Values.nameOverride -}}
{{- if contains $name .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{/*
Create chart name and version as used by the app label.
*/}}
{{- define "spbtemplate.app" -}}
{{- printf "%s-%s" .Chart.Name .Values.image.tag  | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "spbtemplate.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/* Generate secret for spbtemplate */}}
{{- define "spbtemplate.secretData" }}
spring:
  datasource:
    username: {{ .Values.mysql.mysqlUser }}
    password: {{ .Values.mysql.mysqlPassword }}
  liquibase:
    user: {{ .Values.mysql.liquibaseUser }}
    password: {{ .Values.mysql.liquibasePassword }}
{{- end }}


{{/* Generate configuration for spbtemplate */}}
{{- define "spbtemplate.configData" }}
spring:
    datasource:
        url: jdbc:mysql://{{ .Values.mysql.host }}:{{ .Values.mysql.port }}/{{ .Values.mysql.mysqlDatabase }}?useUnicode=true&characterEncoding=utf8&useSSL={{ .Values.mysql.ssl.enabled }}&useLegacyDatetimeCode=false
{{- end }}