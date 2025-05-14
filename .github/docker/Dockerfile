FROM debian:bookworm

# Install ARM64-native build tools and dependencies
RUN apt-get update && apt-get install -y \
    build-essential \
    mesa-common-dev libxxf86vm-dev \
    libxrandr-dev libx11-dev \
    zlib1g ccache gradle openjdk-17-jdk

WORKDIR /build
COPY . .

# Build only ARM64 natives, skipping Android
RUN ./gradlew jnigen jnigenBuildLinuxARM64 \
    -PskipAndroid=true \
    --no-daemon \
    --warning-mode none
