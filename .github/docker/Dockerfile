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

# Prepare output directory and copy built natives to /output
RUN mkdir -p /output/gdx/libs/linuxarm64 && \
    mkdir -p /output/extensions/gdx-bullet/libs/linuxarm64 && \
    mkdir -p /output/extensions/gdx-box2d/libs/linuxarm64 && \
    mkdir -p /output/extensions/gdx-freetype/libs/linuxarm64 && \
    cp -r gdx/libs/linuxarm64/* /output/gdx/libs/linuxarm64/ 2>/dev/null || true && \
    cp -r extensions/gdx-bullet/libs/linuxarm64/* /output/extensions/gdx-bullet/libs/linuxarm64/ 2>/dev/null || true && \
    cp -r extensions/gdx-box2d/gdx-box2d/libs/linuxarm64/* /output/extensions/gdx-box2d/libs/linuxarm64/ 2>/dev/null || true && \
    cp -r extensions/gdx-freetype/libs/linuxarm64/* /output/extensions/gdx-freetype/libs/linuxarm64/ 2>/dev/null || true

# Set /output as the default output directory for GitHub Actions
