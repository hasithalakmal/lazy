{
"status": "${accountStatus}",
"createdBy": "12345",
"parentId": "1",
"enterpriseId": "${enterpriseId}",
"accountName": "${accountName}",
"ownerName": "Hasitha-1577641690",
"versionId": "${versionId}",
"settings": [
<#list settings as setting>
    <#if setting?is_last>
        {
        "key": "${setting.key}",
        "value": "${setting.value}"
        },
    <#else>
        {
        "key": "${setting.key}",
        "value": "${setting.value}"
        },
    </#if>
</#list>
]
}