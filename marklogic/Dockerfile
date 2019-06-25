################################
# This docker file has been modified to include port bindings for ML Data Hub
# Original Repo: https://github.com/alan-johnson/docker-marklogic
################################

# Get the latest CentOS 7 image
FROM centos:7

# Get any CentOS updates then clear the Docker cache
RUN yum -y update && yum clean all

# Install MarkLogic dependencies
RUN yum -y install glibc.i686 gdb.x86_64 redhat-lsb.x86_64 && yum clean all

# install the initscripts package so MarkLogic starts ok
RUN yum -y install initscripts && yum clean all

# Set the Path
ENV PATH /usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/opt/MarkLogic/mlcmd/bin

# Copy the MarkLogic installer to a temp directory in the Docker image being built
COPY MarkLogic-9*.rpm /tmp/MarkLogic.rpm

# Optionally, copy the MarkLogic Converters installer to a temp directory in the Docker image being built
COPY MarkLogicConverters-9*.rpm /tmp/MarkLogicConverters.rpm

COPY initialize-ml.sh /tmp/initialize-ml.sh
RUN chmod +x /tmp/*.sh

# Install MarkLogic then delete the .RPM file if the install succeeded
RUN yum -y install /tmp/MarkLogic.rpm && rm /tmp/MarkLogic.rpm

# Optionally, install MarkLogic Converters then delete the .RPM file if the install succeeded
RUN yum -y install /tmp/MarkLogicConverters.rpm && rm /tmp/MarkLogicConverters.rpm

# Expose MarkLogic Server ports.
# These expose ports between Docker Containers.
# Publish your application ports in the Docker run command using -p (See Docker Documentation)
EXPOSE 7997 8000 8001 8002 8010 8011 8013 8063

# Start MarkLogic and initialize. This creates default databases
# and app servers and also creates the first admin account.
# Also, execute tail such that it waits forever. This prevents
# the container from automatically stopping after starting MarkLogic.
CMD /etc/init.d/MarkLogic start && ./tmp/initialize-ml.sh -u $USER -p $PASS -r public && tail -f /dev/null

