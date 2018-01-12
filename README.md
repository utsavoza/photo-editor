Photo Editor
============

A simple photo editing android application with
[Adobe I/O](https://www.adobe.io/apis/creativecloud/creativesdk.html)'s image editor UI.

Instructions
--------
In order to run the application
- [Register](https://console.adobe.io/) a new app for the Creative SDK.
- Note your **API Key** (Client ID), **Client Secret**, and **Redirect URI**.
- Add a new class called ```Keys``` as shown:
```
      public final class Keys {
        public static final String CREATIVE_SDK_CLIENT_ID = "<YOUR API_KEY HERE>";
        public static final String CREATIVE_SDK_CLIENT_SECRET = "<YOUR CLIENT_SECRET HERE>";
        public static final String CREATIVE_SDK_REDIRECT_URI = "<YOUR REDIRECT_URI HERE>";
        public static final String[] CREATIVE_SDK_SCOPES = { "email", "profile", "address" };
      }
```

More resources for the Creative SDK
-----------------------------------
[Visit Adobe I/O](https://www.adobe.io/apis/creativecloud/creativesdk.html) for component guides,
class references, and more.

Screenshots
-----------
<img src="screenshots/home_activity_framed.png" width="25%" align="left" />
<img src="screenshots/main_activity_framed.png" width="25%" hspace="20" />



License
-------
```
MIT License

Copyright (c) 2017 utsavoza

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
