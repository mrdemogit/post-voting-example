import PropTypes from 'prop-types'

export const CommentType = {
  uuid: PropTypes.string.isRequired,
  author: PropTypes.string.isRequired,
  content: PropTypes.string.isRequired,
  votes: PropTypes.number.isRequired,
  upvote: PropTypes.func,
  downvote: PropTypes.func
}

export const PostType = {
  uuid: PropTypes.string.isRequired,
  author: PropTypes.string.isRequired,
  content: PropTypes.string.isRequired,
  votes: PropTypes.number.isRequired,
  comments: PropTypes.arrayOf(PropTypes.shape(CommentType))
}
