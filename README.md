# Social Media Scroll App

This is an android app built in kotlin, to show of the feed/scrolling feature.It works with my backend - https://github.com/arseniyGoryagin/ScrollApp_backend

## Getting started

Clone the repository

```bash
git clone https://github.com/arseniyGoryagin/ScrollApp
```
Add the ip you want to use and the base url to gradle.properties:

```
IP=YOUR_IP
BASE_URL_DEBUG=YOUR_BASE_URL
BASE_URL_RELEASE=YOUR_BASE_URL
```
The "IP" is used to generate a network security file:

```xml
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">YOUR IP HERE</domain>
    </domain-config>
</network-security-config>
```
In case you have a https url this is not needed, and you can remove this task from build.gradle.kts:

```
  // Network config
    tasks.register<DefaultTask>("generateNetworkSecurityConfig"){

        doLast{

            val ip = project.properties["IP"]
            val file = file("./src/main/res/xml/network_security_config.xml")


            file.writeText(
                    """
                <network-security-config>
                    <domain-config cleartextTrafficPermitted="true">
                        <domain includeSubdomains="true">${ip}</domain>
                    </domain-config>
                </network-security-config>
            """.trimIndent()
                )

        }

    }


    tasks.named("preBuild").configure{
        dependsOn(tasks.named("generateNetworkSecurityConfig"))
    }

```
