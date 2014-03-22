# club

> I don't let everybody in.
> `club.doorman`

> Some things are simply not ok.
> `club.bouncer`

Customizable authorization and permission management for ring request
handlers.

Two idioms are common and supported via `club.doorman` and `club.bouncer`:
- Authorizing a subject for an operation (usually in RBAC like patterns)
- Cancelling an operation, even though a subject has been authorized for it.

The second comes in useful when the first doubles required work:
Deciding what a subject may do and accomplishing it often requires to
perform equal queries twice. It is then preferable to do both in one
place.

`club.doorman` decides based on a subject and a desired operation in a
middleware before the request is passed to a handler

`club.bouncer` decides based on arbitrary assertions in a handler.

`club` comes with no batteries included, simply because there are too
many batteries around. `club` leaves encryption, authorization
workflows, routing etc. entirely to you and makes no assumptions about
your user management.

# Examples
Stay tuned, extensive examples are on their way.

# State

Alpha software. Expect breaking changes. Pull requests and ideas are
welcome.

# License
(http://opensource.org/licenses/MIT)[MIT]