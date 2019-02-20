import React, { PureComponent } from 'react'
import { fetchPosts, postUpvote, postDownvote } from './Datasource'

function withData (WrappedComponent) {
  class ComponentWithData extends PureComponent {
    state = {
      posts: [],
      error: null
    }

    componentDidMount () {
      fetchPosts().subscribe(
        (data) => this.setPosts(data),
        (error) => this.setError(error.message)
      )
    }

    setPosts = (posts) => {
      this.setState({
        posts,
        error: null
      })
    }

    setError = (error) => {
      this.setState({ error })
    }

    upvote = (uuid) => {
      postUpvote(uuid).subscribe(
        (data) => this.setPosts(data),
        (error) => this.setError(error.message)
      )
    }

    downvote = (uuid) => {
      postDownvote(uuid).subscribe(
        (data) => this.setPosts(data),
        (error) => this.setError(error.message)
      )
    }

    render () {
      const { posts, error } = this.state
      return (
        <WrappedComponent
          {...this.props}
          error={error}
          posts={posts}
          upvote={this.upvote}
          downvote={this.downvote}
        />
      )
    }
  }
  ComponentWithData.propTypes = {}
  return ComponentWithData
}

export default withData
