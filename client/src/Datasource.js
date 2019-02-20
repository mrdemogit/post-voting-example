import { ajax } from 'rxjs/ajax'
import { concatMap, toArray } from 'rxjs/operators'

// TODO unit tests
export const fetchPosts = () =>
  ajax({ url: '/api/top', method: 'GET' }).pipe(
    concatMap((ajaxResponse) => ajaxResponse.response),
    toArray()
  )

export const postUpvote = (uuid) =>
  ajax({ url: `/api/upvote/${uuid}`, method: 'POST' }).pipe(
    concatMap((ajaxResponse) => ajaxResponse.response),
    toArray()
  )

export const postDownvote = (uuid) =>
  ajax({ url: `/api/downvote/${uuid}`, method: 'POST' }).pipe(
    concatMap((ajaxResponse) => ajaxResponse.response),
    toArray()
  )
