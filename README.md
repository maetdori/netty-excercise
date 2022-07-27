# 🌐 네티 학습

## 1. 개념

Netty가 실행되는 순서는 다음과 같다. 

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a3434ba7-e3b6-41bf-8f6c-3b1311d66a62/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T081350Z&X-Amz-Expires=86400&X-Amz-Signature=2e71f5ac141ac90f822b5a661c5a1a9b605aa740d06748466180c17955e656a2&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

각각에 대해서 알아보자.

- **Bootstrap**
    - Bootstrap 클래스는 Netty의 스레드를 생성하고, 소켓을 오픈하는 등 Netty를 구동하기 위한 부트스트래핑을 위해 사용하는 클래스다.
- **EventLoopGroup**
    - Netty의 EventLoopGroup은 EventLoop들의 그룹이다.
    - 여러개의 EventLoop은 하나의 그룹으로 모아질 수 있다.
    - 같은 그룹에 속한 EventLoop들은 스레드와 같은 몇몇 리소스들을 공유하게 된다.
- **EventLoop**
    - Netty의 EventLoop은 새로운 이벤트를 반복적으로 확인하는 루프다.
    - 예를 들어 SocketChannel로부터 새로운 데이터가 들어오는 등의 이벤트를 매번 확인한다.
    - 이벤트가 발생하면 적당한 이벤트 핸들러에 전달된다.
- **SocketChannel**
    - Netty의 SocketChannel은 TCP 연결을 대표한다.
    - 네트워크 프로그램에서 Server나 Client가 Netty를 사용한다면, 머신 사이에서 데이터를 전달하는 과정은 SocketChannel을 통해서 이뤄진다.
    - **SocketChannel은 항상 같은 EventLoop에 의해 관리가 된다. 같은 EventLoop은 항상 같은 스레드에서 실행이 되기 때문에 SocketChannel은 항상 같은 스레드에서 접근된다.** **(= 순서가 보장된다.)**
    - 따라서 같은 SocketChannel에서 동시에 데이터가 읽히는 것에 대해서는 걱정하지 않아도 된다.
- **ChannelInitializer**
    - Netty의 ChannelInitializer는 SocketChannel이 생성될 때 ChannelPipeline에 추가되는 특별한 ChannelHandler다. 이 객체는 SocketChannel을 초기화하는 역할을 한다.
    - SocketChannel이 초기화되면 ChannelPipeline에서 ChannelInitializer가 제거된다.
- **ChannelPipeline**
    - 각각의 SocketChannel은 ChannelPipeline을 가지고 있다. ChannelPipeline은 ChannelHandler 인스턴스의 리스트다.
    - EventLoop이 데이터를 SocketChannel에서 읽으면 데이터는 파이프라인에 있는 첫 번째 ChannelHandler에게 넘겨진다. 첫 번째 핸들러는 넘겨받은 데이터를 처리하고, 필요한 경우 파이프라인의 다음 핸들러로 데이터를 넘길 수 있다.
    - 데이터를 SocketChannel로 쓰는 경우도 마찬가지로 ChannelPipeline을 타게 되며, 핸들러들을 거친 다음 SocketChannel로 쓰여지게 된다.
- **ChannelHandler**
    - ChannelHandler는 SocketChannel에서 읽혀진 데이터나 SocketChannel로 쓰여질 데이터를 다루게 된다.       


## 2. ChannelPipeline

채널 파이프라인은 Netty 애플리케이션의 핵심이다. 각 TCP 연결에 해당하는 SocketChannel은 채널 파이프라인을 가지고 있다. 채널 파이프라인은 ChannelHandler 인스턴스의 리스트다. 각 ChannelHandler인스턴스들은 SocketChannel 쪽으로 데이터를 넘기거나, SocketChannel쪽에서 데이터를 얻어온다.

ChannelHandler는 두 가지 서브 인터페이스가 있다. 

- ChannelInboundHandler
- ChannelOutboundHandler

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e3983780-d20d-407c-93df-06fbd9316879/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T081210Z&X-Amz-Expires=86400&X-Amz-Signature=bb6bb6885f219dba26b95f9828f3898b410108ec827e5cf7b459cbbf51c4f00b&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

소켓을 통해서 SocketChannel에 들어온 데이터는 ChannelInboundHandler 체인을 거치면서 애플리케이션으로 넘어온다. 반대로 애플리케이션이 소켓을 통해 전송하려는 데이터는 ChannelOutboundHandler 체인을 거치면서 처리된 후 소켓을 넘어간다. 

위 그림에서는 두 개의 체인이 있는 것처럼 보이지만 사실은 하나의 리스트로 묶여 있고, Inbound와 outbound로 구분되는 형태로 되어있다. 

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/302ab68f-ec96-4e61-bc0c-0bdb6d29fc08/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220727%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220727T081459Z&X-Amz-Expires=86400&X-Amz-Signature=7b656b480fe2ac6a239263a8dab26257e40f4c33a3d6c54ceadfb83bcdd16e2f&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

경우에 따라서는 ChannelInboundHandler에서 넘어온 데이터를 처리하다가 Handler에서 SocketChannel로 다시 데이터를 써서 OutboundHandler를 타고 다시 전송되도록 할 수도 있다.
