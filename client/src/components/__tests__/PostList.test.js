import React from 'react'
import ShallowRenderer from 'react-test-renderer/shallow'
import PostList from '../PostList'

describe('PostList', () => {
  it('should render correctly', () => {
    const renderer = new ShallowRenderer()
    renderer.render(
      <PostList
        posts={[
          {
            uuid: 'uuid1',
            author: 'author1',
            content: 'contentTest1',
            votes: -1,
            comments: [
              {
                uuid: 'uuid13',
                author: 'author13',
                content: 'contentTest13',
                votes: 13
              }
            ]
          },
          {
            uuid: 'uuid3',
            author: 'author3',
            content: 'contentTest3',
            votes: 0,
            comments: []
          }
        ]}
        upvote={() => {}}
        downvote={() => {}}
      />
    )

    expect(renderer.getRenderOutput()).toMatchSnapshot()
  })
})
