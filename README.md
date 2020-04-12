# Base64 file encoder

This simple batch program encodes files (like images) into base64 format so the output can be used in CSS `url()` function. To use it run the following:

`ant resolve assemble`

And copy all the output from `out` directory to anywhere you want and use it like:

`b64 build.xml | clip`

To get more information call:

`b64 -h`
