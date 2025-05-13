# GLIBC 2.36 base
FROM debian:bookworm

# Install ARM64-native build tools
RUN apt-get update && apt-get install -y \
    build-essential \
    mesa-common-dev libxxf86vm-dev \
    libxrandr-dev libx11-dev \
    zlib1g ccache gradle openjdk-17-jdk

WORKDIR /build
COPY . .

# Build only ARM64 desktop natives
RUN ./gradlew jnigen jnigenBuildLinuxARM64 \
    -PskipAndroid=true \
    --no-daemon \
    --warning-mode none

# Copy artifacts with exact directory structure
RUN mkdir -p /output/gdx/libs && \
    mkdir -p /output/extensions && \
    cp -r gdx/libs/linuxarm64 /output/gdx/libs/ && \
    find extensions -name 'linuxarm64' -exec sh -c 'mkdir -p /output/extensions/$$(basename $$(dirname $$(dirname "{}"))) && cp -r "{}" /output/extensions/$$(basename $$(dirname $$(dirname "{}")))/' \;
