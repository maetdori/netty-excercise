# ๐ ๋คํฐ ํ์ต

## 1. ๊ฐ๋

Netty๊ฐ ์คํ๋๋ ์์๋ ๋ค์๊ณผ ๊ฐ๋ค. 

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a3434ba7-e3b6-41bf-8f6c-3b1311d66a62/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T081350Z&X-Amz-Expires=86400&X-Amz-Signature=2e71f5ac141ac90f822b5a661c5a1a9b605aa740d06748466180c17955e656a2&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

๊ฐ๊ฐ์ ๋ํด์ ์์๋ณด์.

- **Bootstrap**
    - Bootstrap ํด๋์ค๋ Netty์ ์ค๋ ๋๋ฅผ ์์ฑํ๊ณ , ์์ผ์ ์คํํ๋ ๋ฑ Netty๋ฅผ ๊ตฌ๋ํ๊ธฐ ์ํ ๋ถํธ์คํธ๋ํ์ ์ํด ์ฌ์ฉํ๋ ํด๋์ค๋ค.
- **EventLoopGroup**
    - Netty์ EventLoopGroup์ EventLoop๋ค์ ๊ทธ๋ฃน์ด๋ค.
    - ์ฌ๋ฌ๊ฐ์ EventLoop์ ํ๋์ ๊ทธ๋ฃน์ผ๋ก ๋ชจ์์ง ์ ์๋ค.
    - ๊ฐ์ ๊ทธ๋ฃน์ ์ํ EventLoop๋ค์ ์ค๋ ๋์ ๊ฐ์ ๋ช๋ช ๋ฆฌ์์ค๋ค์ ๊ณต์ ํ๊ฒ ๋๋ค.
- **EventLoop**
    - Netty์ EventLoop์ ์๋ก์ด ์ด๋ฒคํธ๋ฅผ ๋ฐ๋ณต์ ์ผ๋ก ํ์ธํ๋ ๋ฃจํ๋ค.
    - ์๋ฅผ ๋ค์ด SocketChannel๋ก๋ถํฐ ์๋ก์ด ๋ฐ์ดํฐ๊ฐ ๋ค์ด์ค๋ ๋ฑ์ ์ด๋ฒคํธ๋ฅผ ๋งค๋ฒ ํ์ธํ๋ค.
    - ์ด๋ฒคํธ๊ฐ ๋ฐ์ํ๋ฉด ์ ๋นํ ์ด๋ฒคํธ ํธ๋ค๋ฌ์ ์ ๋ฌ๋๋ค.
- **SocketChannel**
    - Netty์ SocketChannel์ TCP ์ฐ๊ฒฐ์ ๋ํํ๋ค.
    - ๋คํธ์ํฌ ํ๋ก๊ทธ๋จ์์ Server๋ Client๊ฐ Netty๋ฅผ ์ฌ์ฉํ๋ค๋ฉด, ๋จธ์  ์ฌ์ด์์ ๋ฐ์ดํฐ๋ฅผ ์ ๋ฌํ๋ ๊ณผ์ ์ SocketChannel์ ํตํด์ ์ด๋ค์ง๋ค.
    - **SocketChannel์ ํญ์ ๊ฐ์ EventLoop์ ์ํด ๊ด๋ฆฌ๊ฐ ๋๋ค. ๊ฐ์ EventLoop์ ํญ์ ๊ฐ์ ์ค๋ ๋์์ ์คํ์ด ๋๊ธฐ ๋๋ฌธ์ SocketChannel์ ํญ์ ๊ฐ์ ์ค๋ ๋์์ ์ ๊ทผ๋๋ค.** **(= ์์๊ฐ ๋ณด์ฅ๋๋ค.)**
    - ๋ฐ๋ผ์ ๊ฐ์ SocketChannel์์ ๋์์ ๋ฐ์ดํฐ๊ฐ ์ฝํ๋ ๊ฒ์ ๋ํด์๋ ๊ฑฑ์ ํ์ง ์์๋ ๋๋ค.
- **ChannelInitializer**
    - Netty์ ChannelInitializer๋ SocketChannel์ด ์์ฑ๋  ๋ ChannelPipeline์ ์ถ๊ฐ๋๋ ํน๋ณํ ChannelHandler๋ค. ์ด ๊ฐ์ฒด๋ SocketChannel์ ์ด๊ธฐํํ๋ ์ญํ ์ ํ๋ค.
    - SocketChannel์ด ์ด๊ธฐํ๋๋ฉด ChannelPipeline์์ ChannelInitializer๊ฐ ์ ๊ฑฐ๋๋ค.
- **ChannelPipeline**
    - ๊ฐ๊ฐ์ SocketChannel์ ChannelPipeline์ ๊ฐ์ง๊ณ  ์๋ค. ChannelPipeline์ ChannelHandler ์ธ์คํด์ค์ ๋ฆฌ์คํธ๋ค.
    - EventLoop์ด ๋ฐ์ดํฐ๋ฅผ SocketChannel์์ ์ฝ์ผ๋ฉด ๋ฐ์ดํฐ๋ ํ์ดํ๋ผ์ธ์ ์๋ ์ฒซ ๋ฒ์งธ ChannelHandler์๊ฒ ๋๊ฒจ์ง๋ค. ์ฒซ ๋ฒ์งธ ํธ๋ค๋ฌ๋ ๋๊ฒจ๋ฐ์ ๋ฐ์ดํฐ๋ฅผ ์ฒ๋ฆฌํ๊ณ , ํ์ํ ๊ฒฝ์ฐ ํ์ดํ๋ผ์ธ์ ๋ค์ ํธ๋ค๋ฌ๋ก ๋ฐ์ดํฐ๋ฅผ ๋๊ธธ ์ ์๋ค.
    - ๋ฐ์ดํฐ๋ฅผ SocketChannel๋ก ์ฐ๋ ๊ฒฝ์ฐ๋ ๋ง์ฐฌ๊ฐ์ง๋ก ChannelPipeline์ ํ๊ฒ ๋๋ฉฐ, ํธ๋ค๋ฌ๋ค์ ๊ฑฐ์น ๋ค์ SocketChannel๋ก ์ฐ์ฌ์ง๊ฒ ๋๋ค.
- **ChannelHandler**
    - ChannelHandler๋ SocketChannel์์ ์ฝํ์ง ๋ฐ์ดํฐ๋ SocketChannel๋ก ์ฐ์ฌ์ง ๋ฐ์ดํฐ๋ฅผ ๋ค๋ฃจ๊ฒ ๋๋ค.       


## 2. ChannelPipeline

์ฑ๋ ํ์ดํ๋ผ์ธ์ Netty ์ ํ๋ฆฌ์ผ์ด์์ ํต์ฌ์ด๋ค. ๊ฐ TCP ์ฐ๊ฒฐ์ ํด๋นํ๋ SocketChannel์ ์ฑ๋ ํ์ดํ๋ผ์ธ์ ๊ฐ์ง๊ณ  ์๋ค. ์ฑ๋ ํ์ดํ๋ผ์ธ์ ChannelHandler ์ธ์คํด์ค์ ๋ฆฌ์คํธ๋ค. ๊ฐ ChannelHandler์ธ์คํด์ค๋ค์ SocketChannel ์ชฝ์ผ๋ก ๋ฐ์ดํฐ๋ฅผ ๋๊ธฐ๊ฑฐ๋, SocketChannel์ชฝ์์ ๋ฐ์ดํฐ๋ฅผ ์ป์ด์จ๋ค.

ChannelHandler๋ ๋ ๊ฐ์ง ์๋ธ ์ธํฐํ์ด์ค๊ฐ ์๋ค. 

- ChannelInboundHandler
- ChannelOutboundHandler

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e3983780-d20d-407c-93df-06fbd9316879/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T081210Z&X-Amz-Expires=86400&X-Amz-Signature=bb6bb6885f219dba26b95f9828f3898b410108ec827e5cf7b459cbbf51c4f00b&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

์์ผ์ ํตํด์ SocketChannel์ ๋ค์ด์จ ๋ฐ์ดํฐ๋ ChannelInboundHandler ์ฒด์ธ์ ๊ฑฐ์น๋ฉด์ ์ ํ๋ฆฌ์ผ์ด์์ผ๋ก ๋์ด์จ๋ค. ๋ฐ๋๋ก ์ ํ๋ฆฌ์ผ์ด์์ด ์์ผ์ ํตํด ์ ์กํ๋ ค๋ ๋ฐ์ดํฐ๋ ChannelOutboundHandler ์ฒด์ธ์ ๊ฑฐ์น๋ฉด์ ์ฒ๋ฆฌ๋ ํ ์์ผ์ ๋์ด๊ฐ๋ค. 

์ ๊ทธ๋ฆผ์์๋ ๋ ๊ฐ์ ์ฒด์ธ์ด ์๋ ๊ฒ์ฒ๋ผ ๋ณด์ด์ง๋ง ์ฌ์ค์ ํ๋์ ๋ฆฌ์คํธ๋ก ๋ฌถ์ฌ ์๊ณ , Inbound์ outbound๋ก ๊ตฌ๋ถ๋๋ ํํ๋ก ๋์ด์๋ค. 

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/302ab68f-ec96-4e61-bc0c-0bdb6d29fc08/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T081459Z&X-Amz-Expires=86400&X-Amz-Signature=7b656b480fe2ac6a239263a8dab26257e40f4c33a3d6c54ceadfb83bcdd16e2f&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

๊ฒฝ์ฐ์ ๋ฐ๋ผ์๋ ChannelInboundHandler์์ ๋์ด์จ ๋ฐ์ดํฐ๋ฅผ ์ฒ๋ฆฌํ๋ค๊ฐ Handler์์ SocketChannel๋ก ๋ค์ ๋ฐ์ดํฐ๋ฅผ ์จ์ OutboundHandler๋ฅผ ํ๊ณ  ๋ค์ ์ ์ก๋๋๋ก ํ  ์๋ ์๋ค.
