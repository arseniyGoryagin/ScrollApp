# Social Media Scroll App

This is an android app built in kotlin, to show of the feed/scrolling feature.It works with my backend - https://github.com/arseniyGoryagin/ScrollApp_backend

## Getting started

Clone the repository

```bash
git clone https://github.com/arseniyGoryagin/ScrollApp
```

if you want to use it with my backend (https://github.com/arseniyGoryagin/ScrollApp_backend) you should make a file called "network_security_config.xml" in /app/src/main/res/xml/, for andorid to be able to connect to your server ip.

example network_security_config.xml:

```xml
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">YOUR IP HERE</domain>
    </domain-config>
</network-security-config>
```
