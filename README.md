# api-cp-crime-defendant-details

OpenAPI specification for the **Crime Defendant Details API** — read APIs for defendant lookup
on the Common Platform (CP).

> 🔗 API definitions follow the [HMCTS RESTful API Standards](https://hmcts.github.io/restful-api-standards/).

## Purpose

This repository is **specification-only**: it defines the OpenAPI contract, validation tooling,
and publishing workflow for the Crime Defendant Details API. It contains no runtime code. The
matching runtime implementation lives in
[`service-cp-crime-defendant-details`](https://github.com/hmcts/service-cp-crime-defendant-details).

The OpenAPI spec lives at [`src/main/resources/openapi/openapi-spec.yml`](./src/main/resources/openapi/openapi-spec.yml)
and is the single source of truth for this repository. Endpoint definitions are being authored
via the HMCTS API-Marketplace requirements and design stages — see open questions below.

## Consumers

- `service-cp-crime-defendant-details`

## Ownership

- **Owning team:** [`@hmcts/api-marketplace`](https://github.com/orgs/hmcts/teams/api-marketplace) (`maintain`)
- **Repo admin:** [`@hmcts/api-marketplace-admin`](https://github.com/orgs/hmcts/teams/api-marketplace-admin) (`admin`)
- **Product team:** API Marketplace
- **Support model:** In-hours support only
- **Escalation:** Slack `#api-marketplace-support`

## Versioning

- OpenAPI version: **3.1.0**
- API version baseline: **v1.0.0** (SemVer)
- Media type: `application/vnd.hmcts.cp.v1+json`, per
  [HMCTS API Versioning Strategy](https://hmcts.github.io/restful-api-standards/) (media-type
  versioning in the `Accept` header, not the URL path).

## Building

The build validates the OpenAPI spec and generates Java model/client artifacts published as a jar
(`group = uk.gov.hmcts.cp`).

```bash
./gradlew build
```

## Linting

The spec is linted with [Spectral](https://stoplight.io/open-source/spectral) using the
configuration in [`.spectral.yml`](./.spectral.yml):

```bash
npx @stoplight/spectral-cli lint src/main/resources/openapi/openapi-spec.yml
```

Linting also runs in CI via [`.github/workflows/lint-openapi.yml`](./.github/workflows/lint-openapi.yml).

## New team member setup

Anyone newly added to the owning team should verify push access once before contributing:

```bash
gh auth login                                       # if not already authenticated
git clone git@github.com:hmcts/api-cp-crime-defendant-details.git
cd api-cp-crime-defendant-details
git checkout -b smoke/access-check
git commit --allow-empty -m "chore: verify push access"
git push -u origin smoke/access-check
git push origin --delete smoke/access-check          # clean up the throwaway branch
```

If the push is rejected with a permissions error, check team membership of
`@hmcts/api-marketplace` / `@hmcts/api-marketplace-admin` before assuming it's a tooling problem.

## Contributing

Contributions are welcome! Please see the [CONTRIBUTING.md](.github/CONTRIBUTING.md) file for guidelines.

## License

This project is licensed under the [MIT License](LICENSE).