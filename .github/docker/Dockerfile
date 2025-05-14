FROM debian:bookworm

# Install Azul Zulu JDK 8 for ARM64
RUN apt-get update && apt-get install -y wget gnupg ca-certificates && \
    wget -qO - https://repos.azul.com/azul-repo.key | gpg --dearmor -o /usr/share/keyrings/azul.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/azul.gpg arch=arm64] https://repos.azul.com/zulu/deb stable main" > /etc/apt/sources.list.d/zulu.list && \
    apt-get update && apt-get install -y zulu8-jdk

# Find the actual JDK installation path
RUN JAVA_HOME=$(find /usr/lib/jvm -maxdepth 1 -name "zulu*8*" -type d | head -n1) && \
    echo "JAVA_HOME=${JAVA_HOME}" >> /etc/environment
    
# Install build tools
RUN apt-get install -y \
    build-essential \
    mesa-common-dev libxxf86vm-dev \
    libxrandr-dev libx11-dev \
    zlib1g ccache unzip

WORKDIR /build
COPY . .

# Set permissions and fix Gradle version
RUN chmod +x gradlew && \
    ./gradlew wrapper --gradle-version 4.10.3

# RUN ./gradlew wrapper --gradle-version 4.10.3 && \
#    sed -i 's/compile/implementation/g' tests/gdx-tests/build.gradle && \
#    sed -i '/android/d' settings.gradle

# Remove Android modules
RUN ./gradlew wrapper --gradle-version 4.10.3 && \
    sed -i '/android/d' settings.gradle && \
    sed -i '/gdx-tests-android/d' settings.gradle

# Build ARM64 natives
RUN chmod +x gradlew && \
    . /etc/environment && \
    ./gradlew jnigen jnigenBuildLinux64 \
      --no-daemon \
      --refresh-dependencies \
      --stacktrace \
      --info \
      -x test \
      -Dorg.gradle.java.home=${JAVA_HOME}
