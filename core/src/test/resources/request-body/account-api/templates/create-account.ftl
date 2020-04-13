{
"status": "${accountStatus}",
"createdBy": "12345",
"parentId": "1",
"enterpriseId": "${enterpriseId}",
"accountName": "${accountName}",
"ownerName": "Hasitha-1577641690",
"versionId": "${versionId}",
<#if settings??>
    "settings": [
    <#list settings as setting>
        {
        "key": "${setting.key}",
        "value": "${setting.value}"
        }
    </#list>
]
</#if>
}