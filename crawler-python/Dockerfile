FROM python:3.10-alpine as python

# Configure Poetry
ENV POETRY_VERSION=1.4.2

# Install poetry
RUN apk --no-cache add --virtual .build-deps \
    build-base linux-headers libffi-dev
RUN pip3 install poetry==${POETRY_VERSION}

WORKDIR /app

COPY pyproject.toml /app/
COPY poetry.lock /app/
RUN poetry install --no-dev

RUN apk --no-cache del --purge .build-deps

COPY . /app

CMD ["poetry", "run", "python", "main.py"]