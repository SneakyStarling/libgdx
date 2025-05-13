FROM debian:bookworm

# Install ARM64-compatible build tools
RUN apt-get update && apt-get install -y \
    crossbuild-essential-arm64 \
    mesa-common-dev:arm64 libxxf86vm-dev:arm64 \
    libxrandr-dev:arm64 libx11-dev:arm64 \
    zlib1g:arm64 ccache gradle openjdk-17-jdk

WORKDIR /build
COPY . .

# Build natives with Android exclusion
RUN ./gradlew jnigen jnigenBuildLinuxARM64 \
    -PskipAndroid=true \
    --no-daemon \
    --warning-mode none
